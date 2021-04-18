package hu.bme.aut.tvshows.network

import com.google.gson.Gson
import com.google.gson.JsonParseException
import hu.bme.aut.tvshows.network.GsonCustomConverterFactory
import hu.bme.aut.tvshows.network.auth.ApiKeyAuth
import hu.bme.aut.tvshows.network.auth.HttpBasicAuth
import hu.bme.aut.tvshows.network.auth.OAuth
import hu.bme.aut.tvshows.network.auth.OAuth.AccessTokenListener
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.text.DateFormat
import java.util.*

class ApiClient() {
    private var apiAuthorizations: MutableMap<String, Interceptor>
    var okBuilder: OkHttpClient.Builder? = null
        private set
    lateinit var adapterBuilder: Retrofit.Builder
        private set
    private lateinit var json: JSON

    constructor(authNames: Array<String>) : this() {
        for (authName in authNames) {
            throw RuntimeException("auth name \"$authName\" not found in available auth names")
        }
    }

    /**
     * Basic constructor for single auth name
     * @param authName Authentication name
     */
    constructor(authName: String) : this(arrayOf<String>(authName)) {}

    /**
     * Helper constructor for single api key
     * @param authName Authentication name
     * @param apiKey API key
     */
    constructor(authName: String, apiKey: String?) : this(authName) {
        setApiKey(apiKey)
    }

    /**
     * Helper constructor for single basic auth or password oauth2
     * @param authName Authentication name
     * @param username Username
     * @param password Password
     */
    constructor(authName: String, username: String?, password: String?) : this(authName) {
        setCredentials(username, password)
    }

    /**
     * Helper constructor for single password oauth2
     * @param authName Authentication name
     * @param clientId Client ID
     * @param secret Client Secret
     * @param username Username
     * @param password Password
     */
    constructor(
        authName: String,
        clientId: String?,
        secret: String?,
        username: String?,
        password: String?
    ) : this(authName) {
        tokenEndPoint
            ?.setClientId(clientId)
            ?.setClientSecret(secret)
            ?.setUsername(username)
            ?.setPassword(password)
    }

    fun createDefaultAdapter() {
        json = JSON()
        okBuilder = OkHttpClient.Builder()
        var baseUrl = "https://api.tvmaze.com"
        if (!baseUrl.endsWith("/")) baseUrl = "$baseUrl/"
        adapterBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonCustomConverterFactory.create(json.gson))
    }

    fun <S> createService(serviceClass: Class<S>?): S {
        return adapterBuilder
            .client(okBuilder!!.build())
            .build()
            .create(serviceClass)
    }

    fun setDateFormat(dateFormat: DateFormat?): ApiClient {
        json.setDateFormat(dateFormat)
        return this
    }

    fun setSqlDateFormat(dateFormat: DateFormat?): ApiClient {
        json.setSqlDateFormat(dateFormat)
        return this
    }

    fun setOffsetDateTimeFormat(dateFormat: DateTimeFormatter): ApiClient {
        json.setOffsetDateTimeFormat(dateFormat)
        return this
    }

    fun setLocalDateFormat(dateFormat: DateTimeFormatter): ApiClient {
        json.setLocalDateFormat(dateFormat)
        return this
    }

    /**
     * Helper method to configure the first api key found
     * @param apiKey API key
     * @return ApiClient
     */
    fun setApiKey(apiKey: String?): ApiClient {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is ApiKeyAuth) {
                apiAuthorization.apiKey = apiKey
                return this
            }
        }
        return this
    }

    /**
     * Helper method to configure the username/password for basic auth or password oauth
     * @param username Username
     * @param password Password
     * @return ApiClient
     */
    fun setCredentials(username: String?, password: String?): ApiClient {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is HttpBasicAuth) {
                apiAuthorization.setCredentials(username, password)
                return this
            }
            if (apiAuthorization is OAuth) {
                apiAuthorization.tokenRequestBuilder.setUsername(username).setPassword(password)
                return this
            }
        }
        return this
    }

    /**
     * Helper method to configure the token endpoint of the first oauth found in the apiAuthorizations (there should be only one)
     * @return Token request builder
     */
    val tokenEndPoint: TokenRequestBuilder?
        get() {
            for (apiAuthorization in apiAuthorizations.values) {
                if (apiAuthorization is OAuth) {
                    return apiAuthorization.tokenRequestBuilder
                }
            }
            return null
        }

    /**
     * Helper method to configure authorization endpoint of the first oauth found in the apiAuthorizations (there should be only one)
     * @return Authentication request builder
     */
    val authorizationEndPoint: AuthenticationRequestBuilder?
        get() {
            for (apiAuthorization in apiAuthorizations.values) {
                if (apiAuthorization is OAuth) {
                    return apiAuthorization.authenticationRequestBuilder
                }
            }
            return null
        }

    /**
     * Helper method to pre-set the oauth access token of the first oauth found in the apiAuthorizations (there should be only one)
     * @param accessToken Access token
     * @return ApiClient
     */
    fun setAccessToken(accessToken: String?): ApiClient {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is OAuth) {
                apiAuthorization.accessToken = accessToken
                return this
            }
        }
        return this
    }

    /**
     * Helper method to configure the oauth accessCode/implicit flow parameters
     * @param clientId Client ID
     * @param clientSecret Client secret
     * @param redirectURI Redirect URI
     * @return ApiClient
     */
    fun configureAuthorizationFlow(
        clientId: String?,
        clientSecret: String?,
        redirectURI: String?
    ): ApiClient {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is OAuth) {
                val oauth = apiAuthorization
                oauth.tokenRequestBuilder
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setRedirectURI(redirectURI)
                oauth.authenticationRequestBuilder
                    ?.setClientId(clientId)
                    ?.setRedirectURI(redirectURI)
                return this
            }
        }
        return this
    }

    /**
     * Configures a listener which is notified when a new access token is received.
     * @param accessTokenListener Access token listener
     * @return ApiClient
     */
    fun registerAccessTokenListener(accessTokenListener: AccessTokenListener?): ApiClient {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is OAuth) {
                apiAuthorization.registerAccessTokenListener(accessTokenListener)
                return this
            }
        }
        return this
    }

    /**
     * Adds an authorization to be used by the client
     * @param authName Authentication name
     * @param authorization Authorization interceptor
     * @return ApiClient
     */
    fun addAuthorization(authName: String, authorization: Interceptor): ApiClient {
        if (apiAuthorizations.containsKey(authName)) {
            throw RuntimeException("auth name \"$authName\" already in api authorizations")
        }
        apiAuthorizations[authName] = authorization
        okBuilder!!.addInterceptor(authorization)
        return this
    }

    fun getApiAuthorizations(): Map<String, Interceptor> {
        return apiAuthorizations
    }

    fun setApiAuthorizations(apiAuthorizations: MutableMap<String, Interceptor>): ApiClient {
        this.apiAuthorizations = apiAuthorizations
        return this
    }

    fun setAdapterBuilder(adapterBuilder: Retrofit.Builder): ApiClient {
        this.adapterBuilder = adapterBuilder
        return this
    }

    fun addAuthsToOkBuilder(okBuilder: OkHttpClient.Builder?) {
        for (apiAuthorization in apiAuthorizations.values) {
            okBuilder!!.addInterceptor(apiAuthorization)
        }
    }

    /**
     * Clones the okBuilder given in parameter, adds the auth interceptors and uses it to configure the Retrofit
     * @param okClient An instance of OK HTTP client
     */
    fun configureFromOkclient(okClient: OkHttpClient) {
        okBuilder = okClient.newBuilder()
        addAuthsToOkBuilder(okBuilder)
    }

    init {
        apiAuthorizations = LinkedHashMap()
        createDefaultAdapter()
    }
}

/**
 * This wrapper is to take care of this case:
 * when the deserialization fails due to JsonParseException and the
 * expected type is String, then just return the body string.
 */
internal class GsonResponseBodyConverterToString<T>(
    private val gson: Gson,
    private val type: Type
) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val returned = value.string()
        return try {
            gson.fromJson(returned, type)
        } catch (e: JsonParseException) {
            returned as T
        }
    }
}

internal class GsonCustomConverterFactory private constructor(gson: Gson?) : Converter.Factory() {
    private val gson: Gson
    private val gsonConverterFactory: GsonConverterFactory
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type == String::class.java) GsonResponseBodyConverterToString<Any>(
            gson,
            type
        ) else gsonConverterFactory.responseBodyConverter(type, annotations, retrofit)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return gsonConverterFactory.requestBodyConverter(
            type,
            parameterAnnotations,
            methodAnnotations,
            retrofit
        )
    }

    companion object {
        fun create(gson: Gson?): GsonCustomConverterFactory {
            return GsonCustomConverterFactory(gson)
        }
    }

    init {
        if (gson == null) throw NullPointerException("gson == null")
        this.gson = gson
        gsonConverterFactory = GsonConverterFactory.create(gson)
    }
}