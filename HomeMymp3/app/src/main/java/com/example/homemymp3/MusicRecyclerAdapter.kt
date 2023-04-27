package com.example.homemymp3
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homemymp3.databinding.ItemRecyclerviewBinding
import java.text.SimpleDateFormat

class MusicRecyclerAdapter(val context:Context, val musicList:MutableList<MusicData>):
    RecyclerView.Adapter<MusicRecyclerAdapter.CustomViewHolder>() {
    val ALBUM_IMAGE_SIZE = 90
    var like = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = musicList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = holder.binding
        // image, artist, title, duration binding
        val bitmap = musicList.get(position).getAlbumBitmap(context, ALBUM_IMAGE_SIZE)
        if(bitmap != null){
            binding.ivalbumart.setImageBitmap(bitmap)
        }else{
            binding.ivalbumart.setImageResource(R.drawable.music_24)
        }
        binding.tvartist.text = musicList.get(position).artist
        binding.tvtitle.text = musicList.get(position).title
        binding.tvduration.text = SimpleDateFormat("mm:ss").format(musicList.get(position).duration)
        when(musicList.get(position).likes){
            0-> binding.ivitemLike.setImageResource(R.drawable.favorite_like_24)
            1-> binding.ivitemLike.setImageResource(R.drawable.favorite_24)
        }

        // item click 시 PlayActivity MusicData 전달
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context,PlayActivity::class.java)
            val parcelableList : ArrayList<Parcelable>? = musicList as ArrayList<Parcelable>
            intent.putExtra("parcelableList",parcelableList)
            intent.putExtra("position",position)
            context.startActivity(intent)
        }

        // delete button 구현
        binding.root.setOnLongClickListener {
            musicList.removeAt(position)
            notifyDataSetChanged()
            true
        }

        //like button 구현
        binding.ivitemLike.setOnClickListener {
            when(musicList.get(position).likes){
                0->{
                    musicList.get(position).likes = 1
                    binding.ivitemLike.setImageResource(R.drawable.favorite_like_24)
                }
                1->{
                    musicList.get(position).likes = 0
                    binding.ivitemLike.setImageResource(R.drawable.favorite_24)
                }
            }
            val db = DBOpenHelper(context, MainActivity.DB_NAME,MainActivity.VERSION)
            val errorFlag = db.updateLike(musicList.get(position))
            if(errorFlag){
                Toast.makeText(context, "updateLike 실패", Toast.LENGTH_SHORT).show()
            }else{
                this.notifyDataSetChanged()
            }
        }
    }
    inner class CustomViewHolder(val binding:ItemRecyclerviewBinding):RecyclerView.ViewHolder(binding.root)
}