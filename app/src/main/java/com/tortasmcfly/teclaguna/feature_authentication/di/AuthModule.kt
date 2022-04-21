package com.tortasmcfly.teclaguna.feature_authentication.di

import android.app.Application
import androidx.room.Room
import com.tortasmcfly.teclaguna.core.data.remote.BaseUrl
import com.tortasmcfly.teclaguna.feature_authentication.data.local.StudentDatabase
import com.tortasmcfly.teclaguna.feature_authentication.data.remote.AuthApi
import com.tortasmcfly.teclaguna.feature_authentication.data.repository.AuthRepositoryImpl
import com.tortasmcfly.teclaguna.feature_authentication.domain.repository.AuthRepository
import com.tortasmcfly.teclaguna.feature_authentication.domain.use_case.IsLoggedInUseCase
import com.tortasmcfly.teclaguna.feature_authentication.domain.use_case.LoginUseCase
import com.tortasmcfly.teclaguna.feature_authentication.domain.use_case.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(
        repository: AuthRepository
    ): LogoutUseCase {
        return LogoutUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideIsLoggedInUseCase(
        repository: AuthRepository
    ): IsLoggedInUseCase {
        return IsLoggedInUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        database: StudentDatabase
    ): AuthRepository {
        return AuthRepositoryImpl(api, database.dao)
    }

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrl.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabse(
        app: Application
    ): StudentDatabase {
        return Room.databaseBuilder(
            app,
            StudentDatabase::class.java,
            "student_databse"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}