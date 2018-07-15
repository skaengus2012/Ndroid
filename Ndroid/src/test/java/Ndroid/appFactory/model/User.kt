package Ndroid.appFactory.model

/**
 * Define User
 *
 * @author Doohyun
 */
data class User(
        var name: String? = null,
        var age : Int? = null,
        var sn : Long? = null,
        var maleYn : Boolean
) {
    companion object {

        @JvmField
        val defaultUser = create(false, "Default Name")

        /**
         * Create User Factory
         *
         * @param maleYn
         * @param name
         *
         * @return
         */
        @JvmStatic
        fun create(maleYn : Boolean, name: String) =
                User(
                        name = name,
                        maleYn = maleYn,
                        sn = 1,
                        age = 20
                )
    }
}