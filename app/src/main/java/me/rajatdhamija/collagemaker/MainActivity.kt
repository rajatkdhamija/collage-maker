package me.rajatdhamija.collagemaker

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easyfilepicker.Constant
import com.easyfilepicker.activity.ImagePickActivity
import com.easyfilepicker.filter.entity.ImageFile
import com.github.chrisbanes.photoview.PhotoView
import ja.burhanrashid52.photoeditor.OnPhotoEditorListener
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView
import ja.burhanrashid52.photoeditor.ViewType
import kotlinx.android.synthetic.main.activity_main.*
import me.rajatdhamija.collagemaker.photoEditor.TextEditorDialogFragment

class MainActivity : AppCompatActivity(), OnPhotoEditorListener {

    private lateinit var llTwoHorizontal: View
    private lateinit var llTwoVertical: View
    private lateinit var llFourSquare: View
    private lateinit var llFourSquareTypeFive: View
    private lateinit var llFourSquareTypeFour: View
    private lateinit var llFourSquareTypeThree: View
    private lateinit var llFourSquareTypeTwo: View
    private lateinit var llOneTopHorizontalTwoBottom: View
    private lateinit var llThreeHorizontal: View
    private lateinit var llThreeVertical: View
    private lateinit var llTwoCenterBottomOneTop: View
    private lateinit var llTwoCenterTopOneBottom: View
    private lateinit var llTwoLeftThreeRight: View
    private lateinit var llTwoLeftVerticalOneRight: View
    private lateinit var llTwoRightVerticalOneLeft: View
    private lateinit var llTwoTopBottomOneCenter: View
    private lateinit var llTwoTopThreeBottom: View
    private lateinit var imageVerticalOne: PhotoView
    private lateinit var imageVerticalTwo: PhotoView
    private lateinit var imageVerticalThree: PhotoView
    private lateinit var imageVerticalFour: PhotoView
    private lateinit var imageVerticalFive: PhotoView
    private lateinit var imageVerticalOneBitmap: Bitmap
    private lateinit var imageVerticalTwoBitmap: Bitmap
    private lateinit var imageVerticalThreeBitmap: Bitmap
    private lateinit var imageVerticalFourBitmap: Bitmap
    private lateinit var imageVerticalFiveBitmap: Bitmap
    private var mPhotoEditor: PhotoEditor? = null
    private var contrastVal = 1f
    private var brightnessVal = 0f
    private var saturationVal = 1f
    private var imagesArray = ArrayList<String>()
    private var menuType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        imagesArray = intent.getStringArrayListExtra("images") as ArrayList<String>
        setUpLayoutNavigation()
        setUpBottomNavigation()
        setUpPhotoEditorView()
        setUpAdjustSeekBar()
        llAdjust.setOnClickListener {

        }
    }

    private fun setUpAdjustSeekBar() {
        brightness.progressDrawable.setColorFilter(
            Color.parseColor("#6200EE"),
            PorterDuff.Mode.SRC_IN
        )
        brightness.thumb.setColorFilter(Color.parseColor("#6200EE"), PorterDuff.Mode.SRC_IN)
        brightness.progress = 50
        contrast.progressDrawable.setColorFilter(
            Color.parseColor("#6200EE"),
            PorterDuff.Mode.SRC_IN
        )
        contrast.thumb.setColorFilter(Color.parseColor("#6200EE"), PorterDuff.Mode.SRC_IN)
        contrast.progress = 0
        contrast.max = 200

        saturation.progressDrawable.setColorFilter(
            Color.parseColor("#6200EE"),
            PorterDuff.Mode.SRC_IN
        )
        saturation.thumb.setColorFilter(Color.parseColor("#6200EE"), PorterDuff.Mode.SRC_IN)
        saturation.progress = 100
        saturation.max = 200


        brightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                brightnessVal = when {
                    progress < 50 -> {
                        (50 - progress) * -2.55f
                    }
                    progress == 50 -> {
                        0f
                    }
                    else -> {
                        (progress - 50) * 2.55f
                    }
                }
                setAdjustedImages(contrastVal, brightnessVal, saturationVal)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        contrast.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                contrastVal = (progress / 100f)
                setAdjustedImages(contrastVal, brightnessVal, saturationVal)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        saturation.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                saturationVal = (progress / 100f)
                setAdjustedImages(contrastVal, brightnessVal, saturationVal)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


    }

    private fun setAdjustedImages(contrastVal: Float, brightnessVal: Float, saturationVal: Float) {
        when (imagesArray.size) {
            1, 2 -> {
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalOne,
                    imageVerticalOneBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalTwo,
                    imageVerticalTwoBitmap
                )
            }
            3 -> {
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalOne,
                    imageVerticalOneBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalTwo,
                    imageVerticalTwoBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalThree,
                    imageVerticalThreeBitmap
                )
            }
            4 -> {
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalOne,
                    imageVerticalOneBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalTwo,
                    imageVerticalTwoBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalThree,
                    imageVerticalThreeBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalFour,
                    imageVerticalFourBitmap
                )
            }
            5 -> {
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalOne,
                    imageVerticalOneBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalTwo,
                    imageVerticalTwoBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalThree,
                    imageVerticalThreeBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalFour,
                    imageVerticalFourBitmap
                )
                adjustImage(
                    contrastVal,
                    brightnessVal,
                    saturationVal,
                    imageVerticalFive,
                    imageVerticalFiveBitmap
                )
            }
        }
    }

    private fun adjustImage(
        contrastVal: Float,
        brightnessVal: Float,
        saturationVal: Float,
        image: PhotoView,
        bitmap: Bitmap
    ) {
        image.setImageBitmap(
            changeBitmapContrastBrightness(
                bitmap,
                contrastVal,
                brightnessVal,
                saturationVal
            )
        )
    }

    private fun setUpPhotoEditorView() {
        val mPhotoEditorView: PhotoEditorView = findViewById(R.id.photoEditorView)
        mPhotoEditor = PhotoEditor.Builder(this, mPhotoEditorView)
            .setPinchTextScalable(true)
            .build()
        mPhotoEditor?.setOnPhotoEditorListener(this)
    }

    private fun setImagesInViews() {
        try {
            imageVerticalOne = findViewById(R.id.imageVerticalOne)
            imageVerticalTwo = findViewById(R.id.imageVerticalTwo)
            imageVerticalThree = findViewById(R.id.imageVerticalThree)
            imageVerticalFour = findViewById(R.id.imageVerticalFour)
            imageVerticalFive = findViewById(R.id.imageVerticalFive)
        } catch (e: Exception) {

        }
        when (imagesArray.size) {
            1 -> {
                setImage(imagesArray[0], imageVerticalOne)
                setImage(imagesArray[0], imageVerticalTwo)
                Handler().postDelayed({
                    imageVerticalOneBitmap = getBitmapFromView(imageVerticalOne)!!
                    imageVerticalTwoBitmap = getBitmapFromView(imageVerticalTwo)!!
                }, 300)
            }
            2 -> {
                setImage(imagesArray[0], imageVerticalOne)
                setImage(imagesArray[1], imageVerticalTwo)
                Handler().postDelayed({
                    imageVerticalOneBitmap = getBitmapFromView(imageVerticalOne)!!
                    imageVerticalTwoBitmap = getBitmapFromView(imageVerticalTwo)!!
                }, 300)
            }
            3 -> {
                setImage(imagesArray[0], imageVerticalOne)
                setImage(imagesArray[1], imageVerticalTwo)
                setImage(imagesArray[2], imageVerticalThree)
                Handler().postDelayed({
                    imageVerticalOneBitmap = getBitmapFromView(imageVerticalOne)!!
                    imageVerticalTwoBitmap = getBitmapFromView(imageVerticalTwo)!!
                    imageVerticalThreeBitmap = getBitmapFromView(imageVerticalThree)!!
                }, 300)
            }
            4 -> {
                setImage(imagesArray[0], imageVerticalOne)
                setImage(imagesArray[1], imageVerticalTwo)
                setImage(imagesArray[2], imageVerticalThree)
                setImage(imagesArray[3], imageVerticalFour)
                Handler().postDelayed({
                    imageVerticalOneBitmap = getBitmapFromView(imageVerticalOne)!!
                    imageVerticalTwoBitmap = getBitmapFromView(imageVerticalTwo)!!
                    imageVerticalThreeBitmap = getBitmapFromView(imageVerticalThree)!!
                    imageVerticalFourBitmap = getBitmapFromView(imageVerticalFour)!!
                }, 300)
            }
            5 -> {
                setImage(imagesArray[0], imageVerticalOne)
                setImage(imagesArray[1], imageVerticalTwo)
                setImage(imagesArray[2], imageVerticalThree)
                setImage(imagesArray[3], imageVerticalFour)
                setImage(imagesArray[4], imageVerticalFive)
                Handler().postDelayed({
                    imageVerticalOneBitmap = getBitmapFromView(imageVerticalOne)!!
                    imageVerticalTwoBitmap = getBitmapFromView(imageVerticalTwo)!!
                    imageVerticalThreeBitmap = getBitmapFromView(imageVerticalThree)!!
                    imageVerticalFourBitmap = getBitmapFromView(imageVerticalFour)!!
                    imageVerticalFiveBitmap = getBitmapFromView(imageVerticalFive)!!
                }, 300)
            }
        }
    }

    private fun setImage(url: String, view: PhotoView) {
        view.setImageURI(Uri.parse(url))
    }

    private fun setUpBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.layout -> {
                    llAdjust.visibility = View.GONE
                    setUpLayoutNavigation()
                    true
                }

                R.id.adjust -> {
                    hideLayoutNavigation()
                    llAdjust.visibility = View.VISIBLE
                    true
                }

                R.id.text -> {
                    llAdjust.visibility = View.GONE
                    hideLayoutNavigation()
                    val textEditorDialogFragment = TextEditorDialogFragment.show(this)

                    textEditorDialogFragment.setOnTextEditorListener { inputText, colorCode ->
                        mPhotoEditor?.addText(
                            inputText,
                            colorCode
                        )
                    }
                    true
                }
                R.id.image -> {
                    if (imagesArray.size < 5) {
                        val intent1 = Intent(this@MainActivity, ImagePickActivity::class.java)
                        intent1.putExtra(Constant.MAX_NUMBER, 5 - imagesArray.size)
                        startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE)
                    } else {
                        Toast.makeText(this, "Cannot add more than 5 images!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    false
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun hideLayoutNavigation() {
        layout_navigation.visibility = View.GONE
    }

    private fun setUpLayoutNavigation() {
        if (imagesArray.size > 0) {
            layout_navigation.visibility = View.VISIBLE
        } else {
            layout_navigation.visibility = View.GONE
        }
        layout_navigation.setOnNavigationItemSelectedListener { item ->
            llMain.removeAllViews()
            when (item.itemId) {
                R.id.two_horizontal -> {
                    llMain.addView(llTwoHorizontal)
                    setImagesInViews()
                    true
                }
                R.id.two_vertical -> {
                    llMain.addView(llTwoVertical)
                    setImagesInViews()
                    true
                }
                R.id.three_horizontal -> {
                    llMain.addView(llThreeHorizontal)
                    setImagesInViews()
                    true
                }
                R.id.three_vertical -> {
                    llMain.addView(llThreeVertical)
                    setImagesInViews()
                    true
                }
                R.id.two_left_vertical_one_right -> {
                    llMain.addView(llTwoLeftVerticalOneRight)
                    setImagesInViews()
                    true
                }
                R.id.two_right_vertical_one_left -> {
                    llMain.addView(llTwoRightVerticalOneLeft)
                    setImagesInViews()
                    true
                }
                R.id.one_top_horizontal_two_bottom -> {
                    llMain.addView(llOneTopHorizontalTwoBottom)
                    setImagesInViews()
                    true
                }
                R.id.four_square_one -> {
                    llMain.addView(llFourSquare)
                    setImagesInViews()
                    true
                }
                R.id.four_square_type_two -> {
                    llMain.addView(llFourSquareTypeTwo)
                    setImagesInViews()
                    true
                }
                R.id.four_square_type_three -> {
                    llMain.addView(llFourSquareTypeThree)
                    setImagesInViews()
                    true
                }
                R.id.four_square_type_four -> {
                    llMain.addView(llFourSquareTypeFour)
                    setImagesInViews()
                    true
                }
                R.id.four_square_type_five -> {
                    llMain.addView(llFourSquareTypeFive)
                    setImagesInViews()
                    true
                }
                R.id.two_top_three_bottom -> {
                    llMain.addView(llTwoTopThreeBottom)
                    setImagesInViews()
                    true
                }
                R.id.two_top_bottom_one_center -> {
                    llMain.addView(llTwoTopBottomOneCenter)
                    setImagesInViews()
                    true
                }
                R.id.two_center_bottom_one_top -> {
                    llMain.addView(llTwoCenterBottomOneTop)
                    setImagesInViews()
                    true
                }
                R.id.two_center_top_one_bottom -> {
                    llMain.addView(llTwoCenterTopOneBottom)
                    setImagesInViews()
                    true
                }
                R.id.two_left_three_right -> {
                    llMain.addView(llTwoLeftThreeRight)
                    setImagesInViews()
                    true
                }

                else -> {
                    false
                }
            }

        }
        when (imagesArray.size) {
            1, 2 -> inflateMenu(R.menu.two_layout_navigation, 2, R.id.two_horizontal)
            3 -> inflateMenu(R.menu.three_layout_navigation, 3, R.id.three_horizontal)
            4 -> inflateMenu(R.menu.four_layout_navigation, 4, R.id.four_square_one)
            5 -> inflateMenu(R.menu.five_layout_navigation, 5, R.id.two_top_three_bottom)
            else -> {
                layout_navigation.menu.clear()
            }
        }
    }

    private fun inflateMenu(menuId: Int, menuType: Int, selectedItemId: Int) {
        if (this.menuType != menuType) {
            layout_navigation.menu.clear()
            layout_navigation.inflateMenu(menuId)
            layout_navigation.selectedItemId = selectedItemId
            this.menuType = menuType
        }
    }

    private fun initializeViews() {
        val inflater: LayoutInflater =
            this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        llTwoHorizontal = inflater.inflate(
            R.layout.format_two_horizontal,
            findViewById(R.id.llTwoHorizontal)
        )
        setLayoutParams(llTwoHorizontal)
        llTwoVertical = inflater.inflate(
            R.layout.format_two_vertical,
            findViewById(R.id.llTwoVertical)
        )
        setLayoutParams(llTwoVertical)
        llFourSquare = inflater.inflate(
            R.layout.format_four_square,
            findViewById(R.id.llFourSquare)
        )
        setLayoutParams(llFourSquare)
        llFourSquareTypeFive = inflater.inflate(
            R.layout.format_four_square_type_five,
            findViewById(R.id.llFourSquareTypeFive)
        )
        setLayoutParams(llFourSquareTypeFive)
        llFourSquareTypeFour = inflater.inflate(
            R.layout.format_four_square_type_four,
            findViewById(R.id.llFourSquareTypeFour)
        )
        setLayoutParams(llFourSquareTypeFour)
        llFourSquareTypeThree = inflater.inflate(
            R.layout.format_four_square_type_three,
            findViewById(R.id.llFourSquareTypeThree)
        )
        setLayoutParams(llFourSquareTypeThree)
        llFourSquareTypeTwo = inflater.inflate(
            R.layout.format_four_square_type_two,
            findViewById(R.id.llFourSquareTypeTwo)
        )
        setLayoutParams(llFourSquareTypeTwo)
        llOneTopHorizontalTwoBottom = inflater.inflate(
            R.layout.format_one_top_horizontal_two_bottom,
            findViewById(R.id.llOneTopHorizontalTwoBottom)
        )
        setLayoutParams(llOneTopHorizontalTwoBottom)
        llThreeHorizontal = inflater.inflate(
            R.layout.format_three_horizontal,
            findViewById(R.id.llThreeHorizontal)
        )
        setLayoutParams(llThreeHorizontal)
        llThreeVertical = inflater.inflate(
            R.layout.format_three_vertical,
            findViewById(R.id.llThreeVertical)
        )
        setLayoutParams(llThreeVertical)
        llTwoCenterBottomOneTop = inflater.inflate(
            R.layout.format_two_center_bottom_one_top,
            findViewById(R.id.llTwoCenterBottomOneTop)
        )
        setLayoutParams(llTwoCenterBottomOneTop)
        llTwoCenterTopOneBottom = inflater.inflate(
            R.layout.format_two_center_top_one_bottom,
            findViewById(R.id.llTwoCenterTopOneBottom)
        )
        setLayoutParams(llTwoCenterTopOneBottom)
        llTwoLeftThreeRight = inflater.inflate(
            R.layout.format_two_left_three_right,
            findViewById(R.id.llTwoLeftThreeRight)
        )
        setLayoutParams(llTwoLeftThreeRight)
        llTwoLeftVerticalOneRight = inflater.inflate(
            R.layout.format_two_left_vertical_one_right,
            findViewById(R.id.llTwoLeftVerticalOneRight)
        )
        setLayoutParams(llTwoLeftVerticalOneRight)
        llTwoRightVerticalOneLeft = inflater.inflate(
            R.layout.format_two_right_vertical_one_left,
            findViewById(R.id.llTwoRightVerticalOneLeft)
        )
        setLayoutParams(llTwoRightVerticalOneLeft)
        llTwoTopBottomOneCenter = inflater.inflate(
            R.layout.format_two_top_bottom_one_center,
            findViewById(R.id.llTwoTopBottomOneCenter)
        )
        setLayoutParams(llTwoTopBottomOneCenter)
        llTwoTopThreeBottom = inflater.inflate(
            R.layout.format_two_top_three_bottom,
            findViewById(R.id.llTwoTopThreeBottom)
        )
        setLayoutParams(llTwoTopThreeBottom)
    }

    private fun setLayoutParams(view: View) {
        view.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        val returnedBitmap: Bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable: Drawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }
        view.draw(canvas)
        return returnedBitmap
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.REQUEST_CODE_PICK_IMAGE) {
            val list: java.util.ArrayList<ImageFile>? =
                data!!.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE)
            if (list != null) {
                for (image in list) {
                    imagesArray.add(image.path)
                }
                setUpLayoutNavigation()
            }
        }
    }

    override fun onEditTextChangeListener(rootView: View?, text: String?, colorCode: Int) {
        val textEditorDialogFragment = TextEditorDialogFragment.show(this, text!!, colorCode)
        textEditorDialogFragment.setOnTextEditorListener { inputText, colorCode ->
            mPhotoEditor?.editText(
                rootView!!, inputText, colorCode
            )
        }
    }

    override fun onStartViewChangeListener(viewType: ViewType?) {}
    override fun onRemoveViewListener(viewType: ViewType?, numberOfAddedViews: Int) {}
    override fun onAddViewListener(viewType: ViewType?, numberOfAddedViews: Int) {}
    override fun onStopViewChangeListener(viewType: ViewType?) {}
}