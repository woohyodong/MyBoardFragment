package com.woojjajja.myboardfragment.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.woojjajja.myboardfragment.R
import com.woojjajja.myboardfragment.data.Board
import com.woojjajja.myboardfragment.data.BoardDataAdapter
import com.woojjajja.myboardfragment.data.BoardDatabase
import com.woojjajja.myboardfragment.util.AppLogger
import com.woojjajja.myboardfragment.util.CodeHelper
import kotlinx.android.synthetic.main.layout_list.*

class ListFragment: BaseFragment() {

    companion object{
        const val _DATA = "_DATA"
        const val _ID = "_ID"
    }

    lateinit var boardDatabase: BoardDatabase
    lateinit var boardDataAdapter: BoardDataAdapter

    override fun getLayoutID() = R.layout.layout_list

    override fun getAppTitle() = "List(Fragment)"

    override fun setUp() {
        edit_log.setText(CodeHelper.getTimeStamp("yyyy-MM-dd HH:mm:ss")+" 실행")

        boardDatabase = BoardDatabase.getInstance(requireContext())
        boardDataAdapter = BoardDataAdapter(requireContext()){
            (activity as MainActivity).replaceFragment(ViewFragment())
            setFragmentResult("viewKey", bundleOf(_DATA to it))
        }
        boardDataAdapter.setBoardData(boardDatabase.selectAll())
        recyclerview_list.apply {
            adapter = boardDataAdapter
            setHasFixedSize(true)
        }

        button_fab.setOnClickListener {
            (activity as MainActivity).replaceFragment(EditFragment())
        }

        getResult()

    }

    private fun getResult() {

        setFragmentResultListener("listKey"){_,bundle ->
            var mode = bundle.getInt("MODE")
            when(mode){
                1 -> {
                    var board  = bundle.getSerializable(_DATA) as Board
                    board?.let {
                        boardDatabase.insert(it)
                    }
                }
                0 -> {
                    var board  = bundle.getSerializable(_DATA) as Board
                    board?.let {
                        boardDatabase.update(it)
                    }
                }
                -1 -> {
                    var id = bundle.getInt(_ID)
                    boardDatabase.delete(id)
                }
            }
        }
    }
}