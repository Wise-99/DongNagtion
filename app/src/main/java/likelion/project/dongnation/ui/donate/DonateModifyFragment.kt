package likelion.project.dongnation.ui.donate

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import likelion.project.dongnation.R
import likelion.project.dongnation.databinding.FragmentDonateWriteBinding
import likelion.project.dongnation.model.Donations
import likelion.project.dongnation.ui.main.MainActivity

class DonateModifyFragment : Fragment() {

    lateinit var fragmentDonateModifyBinding : FragmentDonateWriteBinding
    lateinit var mainActivity : MainActivity
    private var category = emptyArray<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDonateModifyBinding = FragmentDonateWriteBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        val donate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("donate", Donations::class.java)!!
        } else {
            arguments?.getParcelable("donate")!!
        }

        fragmentDonateModifyBinding.run {
            mainActivity.activityMainBinding.bottomNavigation.visibility = View.GONE

            toolbarDonateWrite.setNavigationOnClickListener {
                mainActivity.removeFragment("DonateModifyFragment")
            }

            category = resources.getStringArray(R.array.array_donate_category)
            spinnerDonateWriteCategory.run {
                adapter = SpinnerAdapter(mainActivity, R.layout.item_spinner, category)
            }

            initModifyDonateInfo(donate)
        }

        return fragmentDonateModifyBinding.root
    }

    fun initModifyDonateInfo(donate : Donations){
        fragmentDonateModifyBinding.run {
            if (donate.donationType == "도와주세요"){
                radioGroupDonateWriteType.check(R.id.radioButton_donate_write_type_help_me)
            } else {
                radioGroupDonateWriteType.check(R.id.radioButton_donate_write_type_help_you)
            }

            when(donate.donationCategory){
                category[1] -> spinnerDonateWriteCategory.setSelection(1)
                category[2] -> spinnerDonateWriteCategory.setSelection(2)
                category[3] -> spinnerDonateWriteCategory.setSelection(3)
                category[4] -> spinnerDonateWriteCategory.setSelection(4)
                category[5] -> spinnerDonateWriteCategory.setSelection(5)
                category[6] -> spinnerDonateWriteCategory.setSelection(6)
                category[7] -> spinnerDonateWriteCategory.setSelection(7)
                category[8] -> spinnerDonateWriteCategory.setSelection(8)
                category[9] -> spinnerDonateWriteCategory.setSelection(9)
                category[10] -> spinnerDonateWriteCategory.setSelection(10)
                category[11] -> spinnerDonateWriteCategory.setSelection(11)
                category[12] -> spinnerDonateWriteCategory.setSelection(12)
            }

            editTextDonateWriteTitle.setText(donate.donationTitle)
            editTextDonateWriteSubTitle.setText(donate.donationSubtitle)
            editTextDonateWriteContent.setText(donate.donationContent)
        }
    }
}