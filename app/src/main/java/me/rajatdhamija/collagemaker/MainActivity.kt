package me.rajatdhamija.collagemaker

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.easyfilepicker.Constant
import com.easyfilepicker.activity.ImagePickActivity
import com.easyfilepicker.filter.entity.ImageFile
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var llTwoHorizontal: View
    lateinit var llTwoVertical: View
    lateinit var llFourSquare: View
    lateinit var llFourSquareTypeFive: View
    lateinit var llFourSquareTypeFour: View
    lateinit var llFourSquareTypeThree: View
    lateinit var llFourSquareTypeTwo: View
    lateinit var llOneTopHorizontalTwoBottom: View
    lateinit var llThreeHorizontal: View
    lateinit var llThreeVertical: View
    lateinit var llTwoCenterBottomOneTop: View
    lateinit var llTwoCenterTopOneBottom: View
    lateinit var llTwoLeftThreeRight: View
    lateinit var llTwoLeftVerticalOneRight: View
    lateinit var llTwoRightVerticalOneLeft: View
    lateinit var llTwoTopBottomOneCenter: View
    lateinit var llTwoTopThreeBottom: View
    private var imagesArray = ArrayList<String>()
    private var menuType = 0

    lateinit var imageVerticalOne: PhotoView
    lateinit var imageVerticalTwo: PhotoView
    lateinit var imageVerticalThree: PhotoView
    lateinit var imageVerticalFour: PhotoView
    lateinit var imageVerticalFive: PhotoView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        imagesArray = intent.getStringArrayListExtra("images") as ArrayList<String>
        setUpLayoutNavigation()
        setUpBottomNavigation()
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
            }
            2 -> {
                setImage(imagesArray[0], imageVerticalOne)
                setImage(imagesArray[1], imageVerticalTwo)
            }
            3 -> {
                setImage(imagesArray[0], imageVerticalOne)
                setImage(imagesArray[1], imageVerticalTwo)
                setImage(imagesArray[2], imageVerticalThree)
            }
            4 -> {
                setImage(imagesArray[0], imageVerticalOne)
                setImage(imagesArray[1], imageVerticalTwo)
                setImage(imagesArray[2], imageVerticalThree)
                setImage(imagesArray[3], imageVerticalFour)
            }
            5 -> {
                setImage(imagesArray[0], imageVerticalOne)
                setImage(imagesArray[1], imageVerticalTwo)
                setImage(imagesArray[2], imageVerticalThree)
                setImage(imagesArray[3], imageVerticalFour)
                setImage(imagesArray[4], imageVerticalFive)
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
                    setUpLayoutNavigation()
                    true
                }
                R.id.text -> {
                    hideLayoutNavigation()
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
        //Define a bitmap with the same size as the view
        val returnedBitmap: Bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        //Get the view's background
        val bgDrawable: Drawable = view.background
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas)
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        }
        // draw the view on the canvas
        view.draw(canvas)
        //return the bitmap
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
}