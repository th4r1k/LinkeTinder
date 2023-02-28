package linketinder.Utils

class Regex {
    static final String name = /[A-z]{4,15}/
    static final String doc = /([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})/
    static final String email = /[\w-\.]+@([\w-]+\.)+[\w-]{2,4}/
    static final String country = /[A-z]{4,15}/
    static final String state = /[A-z]{2,15}/
    static final String zipCode = /^[0-9]{5}-?[0-9]{3}$/
    static final String age = /[0-9]{1,2}/
}
