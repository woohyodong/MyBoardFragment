package com.woojjajja.myboardfragment.ui

import android.text.TextUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.woojjajja.myboardfragment.R
import com.woojjajja.myboardfragment.data.Board
import kotlinx.android.synthetic.main.layout_edit.*

class EditFragment: BaseFragment() {

    private var isUpdate = false
    private var board: Board? = null

    override fun getLayoutID() = R.layout.layout_edit

    override fun getAppTitle() = "Edit(Fragment)"

    override fun setUp() {

        setFragmentResultListener("editKey"){_,bundle ->
            board = bundle.getSerializable(ListFragment._DATA) as Board

            board?.run {
                edit_title.setTag(id)
                edit_title.setText(title)
                isUpdate = true
            }
        }

        button_add.setOnClickListener {

            if(!TextUtils.isEmpty(edit_title.text)){
                board = board ?: Board()
                board?.title = edit_title.text.toString()

                requireActivity().supportFragmentManager.popBackStack()
                setFragmentResult("listKey", bundleOf("MODE" to (if(isUpdate) 0 else 1), ListFragment._DATA to board))
            }
        }
    }
}