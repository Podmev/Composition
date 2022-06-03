package com.podmev.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.podmev.composition.R
import com.podmev.composition.data.GameRepositoryImpl
import com.podmev.composition.databinding.FragmentGameBinding
import com.podmev.composition.domain.entity.GameResult
import com.podmev.composition.domain.entity.GameSettings
import com.podmev.composition.domain.entity.Level
import com.podmev.composition.domain.repository.GameRepository


class GameFragment : Fragment() {
    private lateinit var level: Level
    private lateinit var repository: GameRepository
    private lateinit var gameSettings: GameSettings

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
        initGameState()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            tvOption1.setOnClickListener{
                launchGameFinishedFragment(GameResult(
                    winner = true,
                    countOfRightAnswers = 5,
                    countOfQuestions = 6,
                    gameSettings = gameSettings
                ))
            }
        }
    }

    private fun parseArgs(){
       level = requireArguments().getSerializable(KEY_LEVEL) as Level
    }

    private fun initGameState(){
        repository = GameRepositoryImpl
        gameSettings = repository.getGameSettings(level)
    }

    private fun launchGameFinishedFragment(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object{
        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level): GameFragment{
            return GameFragment().apply{
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL, level)
                }
            }
        }
    }
}
