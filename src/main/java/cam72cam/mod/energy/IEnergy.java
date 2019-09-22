package cam72cam.mod.energy;


import cofh.api.energy.IEnergyStorage;

public interface IEnergy {
    static IEnergy from(IEnergyStorage internal) {
        return new IEnergy() {
            @Override
            public int receiveEnergy(int maxReceive, boolean simulate) {
                return internal.receiveEnergy(maxReceive, simulate);
            }

            @Override
            public int extractEnergy(int maxExtract, boolean simulate) {
                return internal.extractEnergy(maxExtract, simulate);
            }

            @Override
            public int getEnergyStored() {
                return internal.getEnergyStored();
            }

            @Override
            public int getMaxEnergyStored() {
                return internal.getMaxEnergyStored();
            }
        };
    }

    //TODO rename fns
    int receiveEnergy(int maxReceive, boolean simulate);

    int extractEnergy(int maxExtract, boolean simulate);

    int getEnergyStored();

    int getMaxEnergyStored();
}
