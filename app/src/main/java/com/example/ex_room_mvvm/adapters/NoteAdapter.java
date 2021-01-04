package com.example.ex_room_mvvm.adapters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ex_room_mvvm.models.Note;
import com.example.ex_room_mvvm.R;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };
    private OnItemClick listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.mTitle.setText(currentNote.getTitle());
        holder.mDesc.setText(currentNote.getDescription());
        holder.mPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }

    public interface OnItemClick {
        void onItemClick(Note note);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mDesc, mPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.textTitle);
            mDesc = itemView.findViewById(R.id.textDesc);
            mPriority = itemView.findViewById(R.id.textPriority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
}
