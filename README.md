# Example 


```kotlin
            val dialog = NWAlertFragment.newInstance(listOf(
                NWButton(title = "OK", isHighLight = true),
                NWButton("Cancel")
            ),
                title = "Logout",
                message = "Are you sure you want to logout from the application?")
            dialog.options = NWOptions()
            dialog.show(supportFragmentManager)
```