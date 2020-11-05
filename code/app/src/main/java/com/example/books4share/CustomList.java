package com.example.books4share;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<Books> {

    private ArrayList<Books> books;
    private Context context;

    public CustomList(Context context, ArrayList<Books> books) {
        super(context, 0, books);
        this.books = books;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content,parent,false);
        }

        Books book = books.get(position);



        TextView addTitle = view.findViewById(R.id.title_text);
        TextView addAuthor = view.findViewById(R.id.author_text);
        TextView addISBN = view.findViewById(R.id.isbn_text);



        addTitle.setText(book.getTitle());
        addAuthor.setText(book.getAuthor());
        addISBN.setText(book.getISBN());

        return view;
    }

}
