## Features

- [**Drawing**](#drawing) on image with option to change its Brush's Color, Size, Opacity and Erasing.
- Apply [**Filter Effect**](#filter-effect) on image using MediaEffect
- Adding/Editing [**Text**](#text) with option to change its Color with Custom Fonts.
- Adding [**Emoji**](#emoji) with Custom Emoji Fonts.
- Adding [**Images/Stickers**](#adding-imagesstickers)
- Pinch to Scale and Rotate views.
- [**Undo and Redo**](#undo-and-redo) for Brush and Views.
- [**Deleting**](#deleting) Views
- [**Saving**](#saving) Photo after editing.



## Benefits
- Hassle free coding
- Increase efficiency
- Easy image editing


## AndroidX
PhotoEditor ```v.1.0.0``` is a migration to androidX and dropping the support of older support library. There are no API changes. If you find any issue migrating to v.1.0.0 , please follow this [Guide](https://developer.android.com/jetpack/androidx/migrate). If you still facing the issue than you can always rollback to v.0.4.0. Any fix in PR are Welcome :)


## Getting Started
To start with this, we need to simply add the dependencies in the gradle file of our app module like this
```java
implementation 'ja.foto:photoeditor:1.0.0'
```
or we can also import the :photoeditor module from sample for further customization


## Setting up the View
First we need to add `PhotoEditorView` in our xml layout

```xml
 <ja.burhanrashid52.photoeditor.PhotoEditorView
        android:id="@+id/photoEditorView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:photo_src="@drawable/got_s" />
  
```
We can define our drawable or color resource directly using `app:photo_src`

We can set the image programmatically by getting source from `PhotoEditorView` which will return a `ImageView` so that we can load image from resources,file or (Picasso/Glide)
```java
PhotoEditorView mPhotoEditorView = findViewById(R.id.photoEditorView);

mPhotoEditorView.getSource().setImageResource(R.drawable.got);
```

## Building a PhotoEditor
To use the image editing feature we need to build a PhotoEditor which requires a Context and PhotoEditorView which we have to setup in our xml layout


```java
//Use custom font using latest support library
Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);

//loading font from assest
Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");

mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
         .setPinchTextScalable(true)
         .setDefaultTextTypeface(mTextRobotoTf)
         .setDefaultEmojiTypeface(mEmojiTypeFace)
         .build();
 ```
We can customize the properties in the PhotoEditor as per our requirement

| Property  | Usage |
| ------------- | ------------- |
| `setPinchTextScalable()`  | set false to disable pinch to zoom on text insertion.By default its true
| `setDefaultTextTypeface()`  | set default text font to be added on image  |
| `setDefaultEmojiTypeface()`  | set default font specifc to add emojis |

That's it we are done with setting up our library



## Drawing
We can customize our brush and paint with different set of property. To start drawing on image we need to enable the drawing mode

![](https://play-lh.googleusercontent.com/NupQtLOFyZWmLQvGziTss5k-eZ2uELemS9qxNw3sdvi-5Lq3gyHllUQUg4NnEOFJqSLq=w526-h296-rw)

| Type  | Method |
| ------------- | ------------- |
| Enable/Disable  | `mPhotoEditor.setBrushDrawingMode(true);` |
| Bursh Size (px)  | `mPhotoEditor.setBrushSize(brushSize)` |
| Color Opacity (In %)  |   `mPhotoEditor.setOpacity(opacity)`  |
| Brush Color | `mPhotoEditor.setBrushColor(colorCode)`  |
| Brush Eraser  | `mPhotoEditor.brushEraser()` |

**Note**: Whenever we set any property of a brush for drawing it will automatically enable the drawing mode



## Filter Effect
We can apply inbuild filter to the source images using 

 `mPhotoEditor.setFilterEffect(PhotoFilter.BRIGHTNESS);`

![](https://play-lh.googleusercontent.com/EMb9l-R8gThgkWt-qtxjZNIc0mFz9WtV2urPHdzQ9y4bxI8HhfD_0C4a0njRRfS-i2Q=w526-h296-rw)

We can also apply custom effect using `Custom.Builder`

For more details check [Custom Filters](https://github.com/burhanrashid52/PhotoEditor/wiki/Filter-Effect)



## Text

![](https://play-lh.googleusercontent.com/ldv6SGOAZWAL57uCyEicq_qesS7E6Cks75Kaif3JijdLM1_pBMu-O09-uz_PEtHpReo=w526-h296-rw)

We can add the text with inputText and colorCode like this

`mPhotoEditor.addText(inputText, colorCode);` 

It will take default fonts provided in the builder. If we want different fonts for different text we can set typeface with each text like this

`mPhotoEditor.addText(mTypeface,inputText, colorCode);`

In order to edit the text we need the view, which we will receive in our PhotoEditor callback. This callback will trigger when we **Long Press** the added text

 ```java
 mPhotoEditor.setOnPhotoEditorListener(new OnPhotoEditorListener() {
            @Override
            public void onEditTextChangeListener(View rootView, String text, int colorCode) {
                
            }
        });
  ```
Now we can edit the text with a view like this

`mPhotoEditor.editText(rootView, inputText, colorCode);`

If you want more customization on text. Please refer the wiki page for more details.


## Emoji

We can add the Emoji by `PhotoEditor.getEmojis(getActivity());` which will return a list of emojis unicode.

`mPhotoEditor.addEmoji(emojiUnicode);`

It will take default fonts provided in the builder. If we want different Emoji fonts for different emoji we can set typeface with each Emoji like this

`mPhotoEditor.addEmoji(mEmojiTypeface,emojiUnicode);`




## Adding Images/Stickers
 We need to provide a Bitmap to add our Images  `mPhotoEditor.addImage(bitmap);`
 
 
 

## Undo and Redo


 ```java
   mPhotoEditor.undo();
   mPhotoEditor.redo();
 ```
 


## Deleting
  For deleting a Text/Emoji/Image we can click on the view to toggle the view highlighter box which will have a close icon. So, by clicking on the icon we can delete the view.
  
  
  

## Saving
   
   We need to provide a file with callback method when edited image is saved
   
   ```java
    mPhotoEditor.saveAsFile(filePath, new PhotoEditor.OnSaveListener() {
                    @Override
                    public void onSuccess(@NonNull String imagePath) {
                       Log.e("PhotoEditor","Image Saved Successfully");
                    }

                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e("PhotoEditor","Failed to save Image");
                    }
                });
```
#Hey 


PlayStore Link: https://play.google.com/store/apps/details?id=com.innerermond.foto
