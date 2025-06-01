package edu.polytech.td4ex3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Modify by Fred on 16/02/2024.
 */

public class CharacterAdapter extends BaseAdapter {
    private final String TAG = "frallo " + getClass().getSimpleName();
    private List<ValorantCharacter> mangaCharacters;
    private LayoutInflater mInflater;
    private Notifiable callBackActivity;


    public CharacterAdapter(List<ValorantCharacter> mangaCharacters, Notifiable callBackActivity) {
        this.mangaCharacters = mangaCharacters;
        this.callBackActivity = callBackActivity;
        //Log.d(TAG, "callBackActivity = "+callBackActivity.getClass().getSimpleName());
        mInflater = LayoutInflater.from(callBackActivity.getContext());
    }

    public int getCount() {
        return mangaCharacters.size();
    }
    public Object getItem(int position) {
        return mangaCharacters.get(position);
    }
    public long getItemId(int position) {
        return position;
    }


    private void changeViewSize(View view, int width, int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        LinearLayout.LayoutParams constraintParams = (LinearLayout.LayoutParams) params;
        constraintParams.width = (int) (width * callBackActivity.getContext().getResources().getDisplayMetrics().density);
        constraintParams.height = (int) (height * callBackActivity.getContext().getResources().getDisplayMetrics().density);
        view.setLayoutParams(constraintParams);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;

        //(1) : Réutilisation des layouts impossible (mémorisation des valeurs du ratingBar)
        layoutItem = (ConstraintLayout) (convertView == null ? mInflater.inflate(R.layout.character_layout, parent, false) : convertView);
        //layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.character_layout, parent, false);


        //(2) : Récupération des éléments
        TextView name = layoutItem.findViewById(R.id.name);
        View utility = layoutItem.findViewById(R.id.utility);
        View crowdControl = layoutItem.findViewById(R.id.cc);
        View damage = layoutItem.findViewById(R.id.dmg);
        ImageView picture = layoutItem.findViewById(R.id.picture);
        RatingBar ratingBar = layoutItem.findViewById(R.id.ratingBar);

        //(3) : Renseignement des valeurs
        name.setText(mangaCharacters.get(position).getName());
        //picture.setImageResource(mangaCharacters.get(position).getPicture());
        Picasso.get().load(mangaCharacters.get(position).getPictureFace()).into(picture);
        ratingBar.setRating(mangaCharacters.get(position).getValue());

        changeViewSize(utility, (int)(150*0.01*mangaCharacters.get(position).getUtility()), 5);
        changeViewSize(crowdControl, (int)(150*0.01*mangaCharacters.get(position).getCrowdControl()), 5);
        changeViewSize(damage, (int)(150*0.01*mangaCharacters.get(position).getDamage()), 5);

        layoutItem.setOnClickListener( click -> {
            //Log.d(TAG, "clicked on item #"+position);
            callBackActivity.onDataChange(2,position);
        });
        //On retourne l'item créé.
        return layoutItem;
    }


}




