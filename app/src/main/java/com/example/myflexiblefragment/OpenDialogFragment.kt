package com.example.myflexiblefragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_open_dialog.*

/**
 * A simple [Fragment] subclass.
 */
private lateinit var btnChoose: Button
private lateinit var btnClose: Button
private lateinit var rgOptions: RadioGroup
private lateinit var rbSaf: RadioButton
private lateinit var rbMou: RadioButton
private lateinit var rbLvg: RadioButton
private lateinit var rbMoyes: RadioButton
private var optionDialogListener: OnOptionDialogListener? = null

class OnOptionDialogListener {

}

class OpenDialogFragment : DialogFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose =  view.findViewById(R.id.btn_choose)
        btnChoose.setOnClickListener(this)
        btnClose = view.findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        rgOptions = view.findViewById(R.id.rg_options)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbMou = view.findViewById(R.id.rb_mou)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMoyes = view.findViewById(R.id.rb_moyes)

    }
    override fun onAttach(context: Context){
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment){
            val detailCategoryFragment = fragment
            this.optionDialogListener = detailCategoryFragment.optionsDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    override fun onClick(v: View?) {
        when (v.id){
            R.id.btn_close -> dialog?.cancel()

            R.id.btn_choose -> {
                val checkedRadioButtonId = rg_options.checkedRadioButtonId
                if (checkedRadioButtonId != -1){
                    var coach: String? = null
                    when(checkedRadioButtonId){
                        R.id.rb_saf -> coach = rbSaf.text.toString().trim()

                        R.id.rb_mou -> coach = rbMou.text.toString().trim()

                        R.id.rb_lvg -> coach = rbLvg.text.toString().trim()

                        R.id.rb_moyes -> coach = rbMoyes.text.toString().trim()
                    }
                    if (optionDialogListener != null){
                        optionDialogListener?.onOptionChosen(coach)
                    }
                    dialog?.dismiss()
                }
            }
        }
    }
    interface OnOptionDialogListener{
        fun onOptionChosen(text: String?)
    }


}
