import UIKit
import Firebase
import ComposeApp


class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        DI.initialize()
        FirebaseApp.configure()
        return true
    }
}
