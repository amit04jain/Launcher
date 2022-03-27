package com.client.launcher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.launcher.mylibrary.AppInfo;

import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private static Context context;
    private static List<AppInfo> appsList;


    public DrawerAdapter(List<AppInfo> appsList, Context c) {
        context = c;
        this.appsList = appsList;

    }

    public void filterList(List<AppInfo> filterlist) {

        appsList = filterlist;

        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_view_list, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        String appLabel = appsList.get(position).getLabel().toString();
        String appPackage = appsList.get(position).getName().toString();
        Drawable appIcon = appsList.get(position).getIcon();
        String versionName = appsList.get(position).getVersionName();
        long versionCode = appsList.get(position).getVersionCode();


        TextView textView = holder.textView;
        textView.setText(appLabel);
        ImageView imageView = holder.img;
        imageView.setImageDrawable(appIcon);
        holder.appItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackage);
                context.startActivity(intent);
            }
        });
        holder.appItem.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                final CharSequence[] items = {"Uninstall"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Launcher");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        Intent intent = new Intent(Intent.ACTION_DELETE);
                        intent.setData(Uri.parse("package:" + holder.packageNameTextView.getText().toString()));
                        context.startActivity(intent);
                    }
                });
                builder.show();
                return true;
            }
        });

        holder.versionNameTextView.setText(versionName);
        holder.versionCodeTextView.setText("" + versionCode);
        holder.packageNameTextView.setText("" + appPackage);
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView img;
        public TextView versionNameTextView;
        public TextView versionCodeTextView;
        public TextView packageNameTextView;
        public LinearLayout appItem;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_app_name);
            img = itemView.findViewById(R.id.app_icon);
            versionNameTextView = itemView.findViewById(R.id.versionName);
            versionCodeTextView = itemView.findViewById(R.id.versionCode);
            packageNameTextView = itemView.findViewById(R.id.packageName);
            appItem = itemView.findViewById(R.id.appList);


        }


    }

}

