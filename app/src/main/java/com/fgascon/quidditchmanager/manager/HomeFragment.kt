package com.fgascon.quidditchmanager.manager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fgascon.quidditchmanager.data.model.Notification
import com.fgascon.quidditchmanager.data.model.Training
import com.fgascon.quidditchmanager.databinding.FragmentHomeBinding
import com.fgascon.quidditchmanager.manager.adapter.CardItemAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val cardList: List<Notification> = listOf(
            Training("12", "bailable!, ", "url"),
            Training("2", "memorable!, ", "url"),
            Training("1", "runnable!, ", "url"),
            Training("12", "bailable!, ", "url"),
            Training("2", "memorable!, ", "url"),
            Training("1", "runnable!, ", "url"),
            Training("12", "bailable!, ", "url"),
            Training("2", "memorable!, ", "url"),
            Training("1", "runnable!, ", "url"),
            Training("12", "bailable!, ", "url"),
            Training("2", "memorable!, ", "url"),
            Training("1", "runnable!, ", "url"),
            Training("12", "bailable!, ", "url"),
            Training("2", "memorable!, ", "url"),
            Training("1", "runnable!, ", "url")
        )
        Log.d("HOMEFRAGMENT", cardList.size.toString())
        binding.recyclerId.adapter = CardItemAdapter(cardList)


        return binding.root
    }

}