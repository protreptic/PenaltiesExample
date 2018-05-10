package test.p00.presentation.conversation.members.impl.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.calculateDiff
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.conversation.members.impl.adapter.MembersAdapter.MemberViewHolder
import test.p00.presentation.model.conversation.MemberModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class MembersAdapter(
        private var data: List<MemberModel> = listOf(),
        private var delegate: Delegate? = null) : RecyclerView.Adapter<MemberViewHolder>() {

    class Diff(private val old: List<MemberModel>, private val new: List<MemberModel>) : DiffUtil.Callback() {

        override fun getOldListSize() = old.size
        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]

            return old.id == new.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]

            return old == new
        }

    }

    interface Delegate {

        /**
         * Событие наступает когда пользователь
         * выбирает беседу из списка бесед.
         *
         * @param member выбранная беседа
         */
        fun onMemberPicked(member: MemberModel)

    }

    fun changeData(newData: List<MemberModel>): Completable =
            Observable.just(newData)
                    .map { calculateDiff(Diff(data, newData), false) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext { data = newData }
                    .doOnNext { result -> result.dispatchUpdatesTo(this) }
                    .ignoreElements()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MemberViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_conversation_members_member, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = data[position]

        holder.bindMember(member)
        holder.itemView.setOnClickListener {
            delegate?.onMemberPicked(member)
        }
    }

    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val vText: TextView by bindView(R.id.text)

        fun bindMember(member: MemberModel) {
            vText.text = member.name
        }

    }

}