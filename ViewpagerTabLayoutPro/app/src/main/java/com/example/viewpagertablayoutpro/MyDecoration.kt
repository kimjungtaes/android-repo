package com.example.viewpagertablayoutpro

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class MyDecoration(val context:Context):RecyclerView.ItemDecoration() {
    //리사클러뷰에 만들어지고나서 리사클러뷰 위에 화면을 배치
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        //1.리사클러뷰에 사이즈를 가지고 온다
        val recycleWith = parent.width
        val recycleHight = parent.height

        //2.리사클러뷰에 배치할 화면 사이즈
        val imageData: Drawable? = ResourcesCompat.getDrawable(context.resources,R.drawable.kbo, null)
        val imageWidth = imageData?.intrinsicWidth
        val imageHeiht = imageData?.intrinsicHeight

        //3.화면을 배치할 리사이클러뷰 화면 중심점을 구한다
        val locateX =(recycleWith /2) - (imageWidth?.div(2) as Int)
        val locateY =(recycleHight /2) - (imageHeiht?.div(2) as Int)

        //4.캔버스 중앙 위치로 이미지를 그린다
        c.drawBitmap(BitmapFactory.decodeResource(context.resources,R.drawable.kbo),locateX.toFloat(),locateY.toFloat(),
            Paint()
        )


    }
    //아이템뷰를 꾸며주는 역할
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        //1.꾸며야할 아이템뷰의 위치를가져온다
        val index = parent.getChildAdapterPosition(view)

        if(index % 2 == 0){
         outRect.set(10,10,10,50)
        }else{
            outRect.set(0,0,0,0)
        }

        //뿌려져야할 뷰의 배경색을 결정
        view.setBackgroundColor(Color.parseColor("#c26e79"))
        ViewCompat.setElevation(view,60.0f)

    }
}