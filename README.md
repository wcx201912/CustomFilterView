# CustomFilterView

实现页面的筛选功能   

使用方法  

先在 build.gradle(Project:XXXX) 的 repositories 添加 maven { url 'https://jitpack.io' } 一定要加上这个，否则会提示依赖失败

```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


然后在 build.gradle(Module:app) 的 dependencies 添加:implementation 'com.github.wcx201912:CustomFilterView:Tag'

```java
  dependencies {
   compile fileTree(dir: 'libs', include: ['*.jar'])
   implementation 'androidx.appcompat:appcompat:1.0.0'

   /*添加依赖*/
   implementation 'com.github.wcx201912:CustomFilterView:v1.0.0'
}
```

activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <com.wcx.filterView.view.FilterGridView
        android:id="@+id/fgv_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</LinearLayout>
```

MainActivity.java

```java
package com.wcx.filterdemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wcx.filterView.bean.FiltrateBean;
import com.wcx.filterView.view.FilterGridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FilterGridView fgvContent;
    private List<FiltrateBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fgvContent = findViewById(R.id.fgv_content);

        initParam();
        fgvContent.setSingle(false);
        fgvContent.setDatas(datas);
        fgvContent.setTitle("自定义筛选条件view", getResources().getColor(R.color.green_66), 20);
        fgvContent.setOnConfirmClickListener(new FilterGridView.OnConfirmClickListener() {
            @Override
            public void onConfirmClick(List<String> textList, List<Integer> ids) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < textList.size(); i++) {
                    str.append(textList.get(i)).append(" ");
                }
                if (TextUtils.isEmpty(str)) {
                    Toast.makeText(MainActivity.this, "请选择筛选条件", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, str.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //选中
        fgvContent.setOnCheckedTextChangeClickListener(new FilterGridView.OnCheckedTextChangeClickListener() {
            @Override
            public void onCheckedClick(String text, boolean checked) {
                Toast.makeText(MainActivity.this, text + "选择状态：" + checked, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initParam() {
        String[] brand = {"测试数据1", "测试数据2", "测试数据3", "测试数据4", "测试数据5", "测试数据6", "测试数据7", "测试数据8"};
        String[] type = {"数据01", "数据02", "数据03", "数据04", "数据05", "数据06", "数据07", "数据08", "数据09"};
        FiltrateBean fb1 = new FiltrateBean();
        fb1.setTypeName("标题1");
        List<FiltrateBean.Children> childrenList1 = new ArrayList<>();
        for (String aBrand : brand) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aBrand);
            childrenList1.add(cd);
        }
        fb1.setChildren(childrenList1);
        /*————————*/
        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("标题2");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (String aType : type) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aType);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);

        FiltrateBean fb3 = new FiltrateBean();
        fb3.setTypeName("标题3");
        List<FiltrateBean.Children> childrenList3 = new ArrayList<>();
        for (String aBrand : brand) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aBrand);
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);

        FiltrateBean fb4 = new FiltrateBean();
        fb4.setTypeName("标题4");
        List<FiltrateBean.Children> childrenList4 = new ArrayList<>();
        for (String aType : type) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aType);
            childrenList4.add(cd);
        }
        fb4.setChildren(childrenList4);

        FiltrateBean fb5 = new FiltrateBean();
        fb5.setTypeName("标题5");
        List<FiltrateBean.Children> childrenList5 = new ArrayList<>();
        for (String aBrand : brand) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aBrand);
            childrenList5.add(cd);
        }
        fb5.setChildren(childrenList5);

        datas.add(fb1);
        datas.add(fb2);
        datas.add(fb3);
        datas.add(fb4);
        datas.add(fb5);
    }
}
```







