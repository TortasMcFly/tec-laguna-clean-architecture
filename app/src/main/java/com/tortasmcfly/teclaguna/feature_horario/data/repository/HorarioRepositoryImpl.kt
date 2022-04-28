package com.tortasmcfly.teclaguna.feature_horario.data.repository

import android.util.Log
import com.tortasmcfly.teclaguna.core.util.Resource
import com.tortasmcfly.teclaguna.feature_authentication.data.util.Constants
import com.tortasmcfly.teclaguna.feature_horario.data.local.MateriaDao
import com.tortasmcfly.teclaguna.feature_horario.data.local.entity.toDomain
import com.tortasmcfly.teclaguna.feature_horario.data.remote.MateriaApi
import com.tortasmcfly.teclaguna.feature_horario.domain.model.Materia
import com.tortasmcfly.teclaguna.feature_horario.domain.repository.HorarioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import retrofit2.HttpException
import java.io.IOException

class HorarioRepositoryImpl (
    private val api: MateriaApi,
    private val dao: MateriaDao
): HorarioRepository {
    override suspend fun getMaterias(sessionId: String): Flow<Resource<List<Materia>>> = flow {
        emit(Resource.Loading())

        val daoMaterias = dao.getMaterias()
        daoMaterias?.let { materias ->
            emit(Resource.Loading(materias.map { it.toDomain() }))
        }

        try {

            val response = api.getCargaAcademica("${Constants.COOKIE_PREFIX}${sessionId};")

            if(!response.isSuccessful) throw HttpException(response)
            val body = response.body() ?: return@flow

            val html = body.string()
            Log.e("response", html)

            val document = Jsoup.parse(html)
            val name = document.getElementById("__VIEWSTATEGENERATOR").`val`()
            Log.d("__VIEWSTATEGENERATOR", name)

        }
        catch (httpException: HttpException) {
            emit(Resource.Error(httpException.message()))
        }
        catch (ioException: IOException) {
            emit(
                Resource.Error(
                    "No se pudo contactar con el servidor, revisa tu conexión a internet"
                )
            )
        }
    }
}