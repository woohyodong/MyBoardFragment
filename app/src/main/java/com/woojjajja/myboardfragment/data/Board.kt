package com.woojjajja.myboardfragment.data

import com.woojjajja.myboardfragment.util.CodeHelper
import java.io.Serializable

data class Board(val id: Int = CodeHelper.rand(1,100), var title: String = "") : Serializable