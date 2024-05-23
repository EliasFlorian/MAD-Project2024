import com.data.model.MessageResponse
import com.data.model.auth.OAuth2PasswordRequest
import com.data.model.auth.Token
import com.data.model.user.CreateUser
import com.data.model.user.ListUser
import com.data.model.user.SelfUpdateUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    fun login(@Body form: OAuth2PasswordRequest): Call<Token>

    @POST("auth/register")
    fun register(@Body createData: CreateUser): Call<Void>

    @POST("auth/login-guest")
    fun guestAccess(): Call<Token>

    @GET("private/usr/self")
    fun getSelfUser(@Header("Authorization") token: String): Call<ListUser>

    @GET("private/usr/all")
    fun getAllUsers(@Header("Authorization") token: String): Call<List<ListUser>>

    @GET("private/usr/{nickName}")
    fun getUser(@Path("nickName") nickName: String, @Header("Authorization") token: String): Call<ListUser>

    @PUT("/private/usr/self-update")
    fun selfUpdateUser(@Body updateData: SelfUpdateUser, @Header("Authorization") token: String): Call<MessageResponse>

    @PUT("private/usr/upgrade-to-admin")
    fun upgradeUser(@Query("nickName") nickName: String, @Header("Authorization") token: String): Call<MessageResponse>

    @PUT("/private/usr/downgrade-to-regular")
    fun downgradeUser(@Query("nickName") nickName: String, @Header("Authorization") token: String): Call<MessageResponse>

    @DELETE("private/usr/self-delete")
    fun selfDeleteUser(@Header("Authorization") token: String): Call<MessageResponse>

    @DELETE("/private/usr/{nickName}")
    fun deleteUser(@Path("nickName") nickName: String, @Header("Authorization") token: String): Call<MessageResponse>
}