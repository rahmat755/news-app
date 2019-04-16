package com.arthur.newsapp.util

import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet

class DetailsTransition : TransitionSet() {
    init {
        ordering = ORDERING_TOGETHER
        addTransition(ChangeBounds())
        duration = 200
//            addTransition(ChangeTransform())
//            addTransition( ChangeImageTransform())
    }
}

class ReturnTransition : TransitionSet() {
    init {
        ordering = ORDERING_SEQUENTIAL
        addTransition(ChangeBounds())
        duration = 150
    }
}