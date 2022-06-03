package skycode.dicoding.jetpack.mvvm.utils

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by wahyu on 29/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

object UtilPagedList {
    fun <T> mockPagedList(list: List<T>): PagedList<*> {
        val pagedList = mock(PagedList::class.java) as PagedList<*>
        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}