import SwiftUI

import ComposeApp

@main
struct iOSApp: App {

	init() {
		KoinKt.doInitKoin(baseUrl: "test")
		NapierHelperKt.debugBuild()
	}

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}