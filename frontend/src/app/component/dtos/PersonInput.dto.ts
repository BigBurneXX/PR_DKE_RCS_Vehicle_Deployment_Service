export interface PersonInputDto {
    id: number;
    first_name: string;
    last_name: string;
    date_of_birth: string;
    start_address: number;
    target_address: number;
    has_wheelchair: boolean;
    selected?: boolean;
}