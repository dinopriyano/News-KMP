import SwiftUI

import ComposeApp

@main
struct iOSApp: App {

	init() {
		KoinKt.doInitKoin(baseUrl: "test")
	}

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}