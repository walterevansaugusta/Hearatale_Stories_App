package team8304.hearatale_stories;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImages= new ArrayList<>();
    private ArrayList<Integer> mDots = new ArrayList<>();
    private ArrayList<String> mColors = new ArrayList<>();
    private ArrayList<Class> mClasses = new ArrayList<>();
    private ArrayList<String> mFileNames = new ArrayList<>();
    private ArrayList<String> mStoryDescriptions = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<Integer> images, ArrayList<Integer> dots,
                               ArrayList<String> colors, ArrayList<Class> classes, ArrayList<String> fileNames, ArrayList<String> descriptions) {
        mNames = names;
        mImages = images;
        mDots = dots;
        mColors = colors;
        mClasses = classes;
        mContext = context;
        mFileNames = fileNames;
        mStoryDescriptions = descriptions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        Glide.with(mContext)
                .asBitmap()
                .load(mDots.get(position))
                .into(holder.dot);

        holder.name.setText(mNames.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, mClasses.get(position));
                intent.putExtra("title", mNames.get(position));
                intent.putExtra("image", mFileNames.get(position));
                intent.putExtra("description", mStoryDescriptions.get(position));
//                intent.putExtra("class", mContext.);
//                Bundle bundle = new Bundle();
//                bundle.putInt("image", R.drawable.ic_launcher_foreground);
//                intent.putExtras(bundle);
                // intent.putExtra("file", mFileNames.get(position));
                mContext.startActivity(intent);
                // mContext.startActivity(new Intent(mContext, mClasses.get(position)));
            }
        });

        holder.dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mColors.get(position).equalsIgnoreCase("grey")) {
                    Toast.makeText(view.getContext(), "Stories marked with a gray dot feature animals and other non-human protagonists. They will likely appeal equally to both boys and girls.", Toast.LENGTH_LONG).show();
                } else if (mColors.get(position).equalsIgnoreCase("green")) {
                    Toast.makeText(view.getContext(), "Stories marked with a green dot do not emphasize either gender. They may appeal equally to both boys and girls.", Toast.LENGTH_LONG).show();
                } else if (mColors.get(position).equalsIgnoreCase("red")) {
                    Toast.makeText(view.getContext(), "Stories marked with a pink dot emphasize female protagonists. They may appeal more to girls.", Toast.LENGTH_LONG).show();
                } else if (mColors.get(position).equalsIgnoreCase("blue")) {
                    Toast.makeText(view.getContext(), "Stories marked with a blue dot emphasize male protagonists. They may appeal more to boys.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        CircleImageView image;
        ImageView dot;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
            dot = itemView.findViewById(R.id.dot);
        }
    }
}