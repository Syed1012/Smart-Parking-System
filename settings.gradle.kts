plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "smart-parking-system"
include("user-service", "parkingManagement-service", "booking-service", "notification-service", "payment-service", "monitoring-service", "report-service")
