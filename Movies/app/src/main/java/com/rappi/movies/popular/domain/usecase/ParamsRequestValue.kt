package com.rappi.movies.popular.domain.usecase

import com.rappi.movies.UseCase

class ParamsRequestValue(val listId: String,
                         val page:String?): UseCase.RequestValue {
}