package me.rajatdhamija.collagemaker

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.easyfilepicker.Constant.*
import com.easyfilepicker.activity.ImagePickActivity
import com.easyfilepicker.filter.entity.ImageFile
import java.util.ArrayList

class ImagePickerActivity : AppCompatActivity() {

    private var images = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)
        val intent1 = Intent(this, ImagePickActivity::class.java)
        intent1.putExtra(MAX_NUMBER, 5)
        startActivityForResult(intent1, REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE){
            val list: ArrayList<ImageFile>? =
                data!!.getParcelableArrayListExtra(RESULT_PICK_IMAGE)
            if (list != null) {
                for (image in list){
                    images.add(image.path)
                }
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("images", images)
                startActivity(intent)
            }
        }
    }
}