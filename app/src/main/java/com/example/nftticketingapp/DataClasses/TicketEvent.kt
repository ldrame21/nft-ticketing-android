package com.example.nftticketingapp.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketEvent(
    var event: Event?,
    var ticket: Ticket?
): Parcelable
