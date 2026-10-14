package Liberary;

class BookLimitExceededException extends Exception{
    public BookLimitExceededException(String msg){
        super(msg);
    }
}

class BookUnavailableException extends Exception{
    public BookUnavailableException(String msg){
        super(msg);
    }
}

class BookNotBorrowedException extends Exception{
    public BookNotBorrowedException(String msg){
        super(msg);
    }
}

class MemberNotBorrowingException extends Exception{
    public MemberNotBorrowingException(String msg){
        super(msg);
    }
}

class AlreadyBorrowedException extends Exception{
    public AlreadyBorrowedException(String msg){
        super(msg);
    }
}

public class LiberaryExceptions {
}
