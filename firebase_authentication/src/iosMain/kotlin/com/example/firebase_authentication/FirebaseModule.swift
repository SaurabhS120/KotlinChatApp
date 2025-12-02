import Foundation
import FirebaseCore

public final class FirebaseModule {
    public static func initialize() {
        if FirebaseApp.app() == nil {
            FirebaseApp.configure()
        }
    }
}
