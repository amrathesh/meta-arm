require recipes-bsp/uefi/edk2-firmware_202102.bb

PROVIDES:remove = "virtual/uefi-firmware"

LICENSE += "& Apache-2.0"
LIC_FILES_CHKSUM += "file://ShellPkg/Application/bsa-acs/LICENSE.md;md5=2a944942e1496af1886903d274dedb13"

SRC_URI += "git://github.com/ARM-software/bsa-acs;destsuffix=edk2/ShellPkg/Application/bsa-acs;protocol=https;branch=release;name=acs \
            git://github.com/tianocore/edk2-libc;destsuffix=edk2/edk2-libc;protocol=https;branch=master;name=libc \
            file://ir_bsa.patch \
            file://edk2_gicv3.patch \
            "

SRCREV_acs = "6565ee330513ce54ebdac8df0a77d5009adf0fac"
SRCREV_libc = "61687168fe02ac4d933a36c9145fdd242ac424d1"

COMPATIBLE_HOST = "aarch64.*-linux"
EDK2_ARCH = "AARCH64"
EDK2_PLATFORM = "Shell"
EDK2_PLATFORM_DSC = "ShellPkg/ShellPkg.dsc"
EDK2_EXTRA_BUILD = "--module ShellPkg/Application/bsa-acs/uefi_app/BsaAcs.inf"

PACKAGES_PATH .= ":${S}/edk2-libc"

do_install() {
    install -d ${D}/firmware
    install ${B}/Build/${EDK2_PLATFORM}/${EDK2_BUILD_MODE}_${EDK_COMPILER}/*/Bsa.efi ${D}/firmware/
}
