package skycode.dicoding.jetpack.mvvm.ui.tv_show

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import skycode.dicoding.jetpack.mvvm.usecase.DataUseCase

/**
 * Created by wahyu on 28/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

class TvShowViewModel @ViewModelInject constructor(
    private val useCase: DataUseCase
): ViewModel() {

    fun getTvShows() = useCase.getTvShows()
    fun getFailureMessage() = useCase.failureMessage
    fun getIsLoadData() = useCase.onIsLoadData
}