export interface PersonDto {
  id: number;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  startAddress: string;
  targetAddress: string;
  hasWheelchair: boolean;
  selected?: boolean;
}
