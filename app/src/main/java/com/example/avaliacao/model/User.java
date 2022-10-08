package com.example.avaliacao.model;

import android.os.Parcel;
import android.os.Parcelable;

// implementando a interface Parcelable

public class User implements Parcelable {

    // criando os atributos
    private int id;
    private String name;
    private String username;
    private String email;

    // criando o construtor
    public User(int id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }
    // criado os get's
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

//criando o metodo construtor a partir de um parcelable
    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        email = in.readString();
    }
//metodo criador do parcel
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

// manda incluir em um parcel os atributos
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(email);
    }
}


