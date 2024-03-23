export class ApiEndpoints{
  private static readonly base= "http://localhost:8080/spring-api";

  static readonly register= ApiEndpoints.base + "/auth/register";
  static readonly login= ApiEndpoints.base + "/auth/login";
  static user: any;

}
