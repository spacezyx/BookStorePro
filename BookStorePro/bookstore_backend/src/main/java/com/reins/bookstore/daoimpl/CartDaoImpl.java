package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.CartDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.repository.BookRepository;
import com.reins.bookstore.repository.CartRepository;
import com.reins.bookstore.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<CartResult> getCartsByUser_id(Integer user_id) {
        List<Cart> re = cartRepository.getCarts(user_id);
        List<CartResult> result= new ArrayList<>();
        for(int i=0;i<re.size();i++){
            Integer tmp = re.get(i).getBook_id();
            Integer num = re.get(i).getNum();
            Book b = bookRepository.getOne(tmp);
            CartResult c = new CartResult(user_id,b,num);
            result.add(c);
        }
        System.out.println(result.toString());
        return result;
    }



    @Override
    public void addCart(Integer user_id,Integer book_id,Integer num){
        cartRepository.addCart(user_id,book_id,num);
    }

    @Override
    public void cleanCartByUser_Id(Integer user_id){
        cartRepository.cleanCartByUser_Id(user_id);
    }

}
