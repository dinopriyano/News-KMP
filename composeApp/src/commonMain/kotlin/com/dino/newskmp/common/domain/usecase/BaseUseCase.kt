package com.dino.newskmp.common.domain.usecase

import com.dino.newskmp.common.data.remote.Resource
import com.dino.newskmp.common.data.remote.SafeApiCall
import com.dino.newskmp.platform.ioDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<out Type, in Params>: SafeApiCall {

    abstract suspend fun run(params: Params): Resource<Type>

    operator fun invoke(
        params: Params,
        coroutineContext: CoroutineContext = ioDispatcher,
        onResult: (Flow<Resource<Type>>) -> Unit = {}
    ) {
        onResult(
            execute(coroutineContext) {
                run(params)
            }
        )
    }

    private fun <T> execute(
        context: CoroutineContext,
        block: suspend () -> T
    ): Flow<T> {
        return flow {
            val out = block.invoke()
            emit(out)
        }.flowOn(context)
    }
}