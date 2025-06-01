package edu.polytech.td4ex3;

import android.content.Context;

public interface Notifiable {
    void onClick(int numFragment);
    void onDataChange(int numFragment, Object object);

    Context getContext();
}
