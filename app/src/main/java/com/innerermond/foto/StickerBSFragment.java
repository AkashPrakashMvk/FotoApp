package com.innerermond.foto;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class StickerBSFragment extends BottomSheetDialogFragment {



    public static String a = "a";
   int[] stickerif;

    public StickerBSFragment() {
        // Required empty public constructor
    }



    private StickerListener mStickerListener;


    public void setStickerListener(StickerListener stickerListener) {
        mStickerListener = stickerListener;
    }

    public interface StickerListener {

        void onStickerClick(Bitmap bitmap);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {

            if (newState == BottomSheetBehavior.STATE_HIDDEN) {

                dismiss();
            }


        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sticker_emoji_dialog, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.white));
        RecyclerView rvEmoji = contentView.findViewById(R.id.rvEmoji);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvEmoji.setLayoutManager(gridLayoutManager);
        StickerAdapter stickerAdapter = new StickerAdapter();
        rvEmoji.setAdapter(stickerAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {



        int[] stickerList  = EditImageActivity.stickerifelse;

          /*  if(a == "hairs"){

            stickerif   = new int[]{R.drawable.h1,R.drawable.h2,R.drawable.h3,
                    R.drawable.h4,R.drawable.h5,R.drawable.h6,
                    R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
                    R.drawable.h11,R.drawable.h12,R.drawable.h13,R.drawable.h14,
                    R.drawable.h15,R.drawable.h16,R.drawable.h17,R.drawable.h18,
                    R.drawable.h19,R.drawable.h20,R.drawable.h21,R.drawable.h22,
                    R.drawable.h23,R.drawable.h24,R.drawable.h25,R.drawable.h26,
                    R.drawable.h27,R.drawable.h28,R.drawable.h30,R.drawable.h31,
                    R.drawable.h32,R.drawable.h33,R.drawable.h34,R.drawable.h35,
                    R.drawable.h36,R.drawable.h37,R.drawable.h38,R.drawable.h39,
                    R.drawable.h40,R.drawable.h41,};

        }
            else if(a == "beards"){
            stickerif   = new int[]{R.drawable.b1,R.drawable.b2,R.drawable.b3,
                    R.drawable.b4,R.drawable.b5,R.drawable.b6,
                    R.drawable.b7,R.drawable.b8,R.drawable.b9,R.drawable.b10,
                    R.drawable.b11,R.drawable.b12,R.drawable.b13,R.drawable.b14,
                    R.drawable.b15,R.drawable.b16,R.drawable.b17,R.drawable.b18,
                    R.drawable.b19,R.drawable.b20,R.drawable.b21,R.drawable.b22,
                    R.drawable.b23,R.drawable.b24,R.drawable.b25,R.drawable.b26,
                    R.drawable.b27,R.drawable.b28,R.drawable.b30,R.drawable.b31,
                    R.drawable.b32,R.drawable.b33,R.drawable.b34,R.drawable.b35,
                    R.drawable.b36,R.drawable.b37,R.drawable.b38,R.drawable.b39,
                    R.drawable.b40,R.drawable.b41, R.drawable.b42,R.drawable.b43,
                    R.drawable.b44,R.drawable.b45,};


        }
            else{
            stickerif   = new int[]{R.drawable.h1,R.drawable.h2,R.drawable.h3,
                    R.drawable.h4,R.drawable.h5,R.drawable.h6,
                    R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
                    R.drawable.h11,R.drawable.h12,R.drawable.h13,R.drawable.h14,
                    R.drawable.h15,R.drawable.h16,R.drawable.h17,R.drawable.h18,
                    R.drawable.h19,R.drawable.h20,R.drawable.h21,R.drawable.h22,
                    R.drawable.h23,R.drawable.h24,R.drawable.h25,R.drawable.h26,
                    R.drawable.h27,R.drawable.h28,R.drawable.h30,R.drawable.h31,
                    R.drawable.h32,R.drawable.h33,R.drawable.h34,R.drawable.h35,
                    R.drawable.h36,R.drawable.h37,R.drawable.h38,R.drawable.h39,
                    R.drawable.h40,R.drawable.h41,};
        }
*/



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sticker, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imgSticker.setImageResource(stickerList[position]);
        }

        @Override
        public int getItemCount() {
            return stickerList.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgSticker;

            ViewHolder(View itemView) {
                super(itemView);
                imgSticker = itemView.findViewById(R.id.imgSticker);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mStickerListener != null) {
                            mStickerListener.onStickerClick(
                                    BitmapFactory.decodeResource(getResources(),
                                            stickerList[getLayoutPosition()]));
                        }
                        dismiss();
                    }
                });
            }
        }
    }

    private String convertEmoji(String emoji) {
        String returnedEmoji = "";
        try {
            int convertEmojiToInt = Integer.parseInt(emoji.substring(2), 16);
            returnedEmoji = getEmojiByUnicode(convertEmojiToInt);
        } catch (NumberFormatException e) {
            returnedEmoji = "";
        }
        return returnedEmoji;
    }

    private String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}