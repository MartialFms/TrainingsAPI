import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Category } from '../model/category';
import { Order } from '../model/order';
import { OrderItem } from '../model/orderItem';
import { Training } from '../model/training';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private http: HttpClient) {}

  //get all trainings from api
  public getTrainings() {
    return this.http.get<Training[]>(environment.host + '/trainings');
  }

  //get training by id
  public getOneTraining(id: number) {
    return this.http.get<Training>(environment.host + '/training/' + id);
  }

  //add one training
  public add(trainingData: FormData) {
    return this.http.post<Training>(
      environment.host + '/trainings',
      trainingData
    );
  }

  //delete
  public deleteTr(id: number) {
    return this.http.delete<Training>(environment.host + '/trainings/' + id);
  }

  public update(id: number, trainingData: FormData) {
    return this.http.put<Training>(
      environment.host + '/training/' + id,
      trainingData
    );
  }

  public getByCategories(id: number) {
    return this.http.get<Training[]>(
      environment.host + '/categorie/' + id + '/trainings'
    );
  }

  public addOrder(order: Order) {
    return this.http.post<Order>(environment.host + '/order', order);
  }

  public getCategories() {
    return this.http.get<Category[]>(environment.host + '/categories');
  }

  public getOneCategory(id: number) {
    return this.http.get<Category>(environment.host + '/category/' + id);
  }

  public getTrainingImage(id: number) {
    return this.http.get(environment.host + '/trainingImage/' + id);
  }

  public getAllOrders() {
    return this.http.get<Order[]>(environment.host + '/orders');
  }

  public getOneOrder(id: number) {
    return this.http.get<Order>(environment.host + '/order/' + id);
  }

  public getOrderItems(id: number) {
    return this.http.get<OrderItem[]>(environment.host + '/orderItems/' + id);
  }
}
