import SwiftUI

import ComposeApp

@main
struct iOSApp: App {

	init() {
		KoinKt.doInitKoin()
		NapierHelperKt.debugBuild()
	}

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}