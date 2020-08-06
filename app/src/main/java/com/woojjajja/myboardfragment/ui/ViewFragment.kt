package com.woojjajja.myboardfragment.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.woojjajja.myboardfragment.R
import com.woojjajja.myboardfragment.data.Board
import kotlinx.android.synthetic.main.layout_view.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton

class ViewFragment: BaseFragment() {

    private var board: Board? = null

    override fun getLayoutID() = R.layout.layout_view

    override fun getAppTitle() = "View(Fragment)"

    override fun setUp() {

        setHasOptionsMenu(true)

        setFragmentResultListener("viewKey"){_, bundle ->
            board = bundle.getSerializable(ListFragment._DATA) as Board

            board?.run {
                text_title.setTag(id)
                text_title.setText(title)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_board,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_modify -> {
                requireActivity().supportFragmentManager.popBackStack()
                (activity as MainActivity).replaceFragment(EditFragment())
                setFragmentResult("editKey", bundleOf(ListFragment._DATA to board))

            }
            R.id.menu_remove -> {
                alert("삭제하시겠습니까?"){
                    yesButton {
                        requireActivity().supportFragmentManager.popBackStack()
                        setFragmentResult("listKey", bundleOf("MODE" to -1, ListFragment._ID to board?.id))
                    }
                }.show()
            }
        }

        return false
        //return super.onOptionsItemSelected(item)
    }
}