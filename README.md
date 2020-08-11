# Gallery_Android
안드로이드 이미지 리사클러뷰

retrofit을 이용한 endless 이미지 recyclerview 제작 샘플

사용라이브러리
=============

<pre>
<code>
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'

    implementation 'com.github.bumptech.glide:glide:4.3.1'
    
    // design:28.0.0
    //noinspection GradleCompatible
    implementation 'com.google.android.material:material:1.0.0'
</code>
</pre>

Json Response 형태
=============
<pre>
<code>
{
    "items": [
        {
            "id": "9",
            "shop_id": "1",
            "os": "WEB",
            "item_status": "판매중",
            "item_brand": "루이비통",
            "item_category": "가방",
            "item_name": "루이비통 가방",
            "item_price": "10",
            "item_memo": "루이비통 가방입니다",
            "item_img1": "https://www.louisvuitton.com/images/M45355_PM2_Front%20view?wid=656&hei=656",
            "item_img2": "https://www.louisvuitton.com/images/M45355_PM2_Front%20view?wid=656&hei=656",
            "item_img3": "https://www.louisvuitton.com/images/M45355_PM2_Front%20view?wid=656&hei=656",
            "item_img4": "https://www.louisvuitton.com/images/M45355_PM2_Front%20view?wid=656&hei=656",
            "item_img5": "https://www.louisvuitton.com/images/M45355_PM2_Front%20view?wid=656&hei=656",
            "created_at": "2020-08-07 17:34:36",
            "updated_at": "2020-08-07 17:34:36"
        },
        ...
    ],
    "current_page": 1,
    "next_page": 2,
    "total_page": 2,
    "total": "9"
}
</code>
</pre>