package com.example.oneul

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.oneul.data.Diary
import com.example.oneul.databinding.FragmentDailyDiaryBinding
import com.example.oneul.viewmodel.DiaryViewModel
import com.example.oneul.viewmodel.DiaryViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.google.android.play.core.install.model.ActivityResult
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_daily_diary.*
import kotlinx.android.synthetic.main.fragment_daily_diary.view.*

class DailyDiaryFragment : Fragment() {

    private lateinit var binding: FragmentDailyDiaryBinding
    private lateinit var diaryViewModel: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_diary, container, false)

        diaryViewModel = ViewModelProvider(requireActivity()).get(DiaryViewModel::class.java)

        diaryViewModel.currentDiary.observe(viewLifecycleOwner, Observer { diary ->
            view.textView.text = diary.date
            view.textView2.text = diary.diary
            view.imageView6.setImageDrawable(diary.mood?.let { requireContext().getDrawable(it) })
            view.imageView4.setImageURI(diary.imagesUrl?.toUri())
        })

        view.imageButton.setOnClickListener {
            activity?.onBackPressed()
        }

        view.imageView4.setOnClickListener() {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && data != null){
            if(requestCode == 1){
                var imageUri : Uri? = data?.data
                imageView4.setImageURI(imageUri)
                diaryViewModel.currentDiary.value?.imagesUrl = imageUri.toString()
                diaryViewModel.notifyCurrentDiary()
            }
        }
    }
}