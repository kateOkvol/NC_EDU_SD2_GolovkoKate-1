export interface UserModel {
  id?: number;
  email: string;
  userName: string;
  password: string;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  birthDate?: Date;
  gender?: string;
  isBan?: boolean;
  isDeleted?: boolean;
  role?: string;
}
