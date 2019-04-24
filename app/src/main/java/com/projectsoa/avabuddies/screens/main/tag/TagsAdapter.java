package com.projectsoa.avabuddies.screens.main.tag;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectsoa.avabuddies.R;
import com.projectsoa.avabuddies.data.models.Tag;
import com.projectsoa.avabuddies.data.repositories.LoginRepository;

import java.util.List;

import javax.inject.Inject;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {

    private final Context context;
    private final TagsInteractionListener listener;
    @Inject
    protected LoginRepository loginRepository;
    private List<Tag> tagList;
    private List<Tag> selectedTags;

    public TagsAdapter(Context context, TagsInteractionListener listener) {
        this.context = context;
        this.listener = listener;
    }


    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }


    public void setSelectedTags(List<Tag> selectedTags) {
        this.selectedTags = selectedTags;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Tag tag = tagList.get(position);
        holder.tag = tag;
        holder.name.setText(tag.getName());
        boolean isSelected = false;
        for(Tag selectTag : this.selectedTags){
            if(selectTag.get_id().equals(tag.get_id())){
                isSelected = true;
            }
        }
        if(isSelected){
            holder.name.setBackgroundColor(Color.BLUE);
        }else{
            holder.name.setBackgroundColor(Color.TRANSPARENT);
        }

    }

    @Override
    public int getItemCount() {
        return tagList == null ? 0 : tagList.size();
    }

    public interface TagsInteractionListener {
        void onUserListInteract(Tag tag);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public Tag tag;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textName);

            if (listener != null) {
                view.setOnClickListener(v -> listener.onUserListInteract(tag));
            }
        }
    }

}